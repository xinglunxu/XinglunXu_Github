# xSpim Memory Demo Program

#  Data Area
.data

welcome:
   .asciiz "Welcome.  This program performs various functions on an array.\n"

value:
    .asciiz "\nValue: "

array:
    .asciiz "\nArray contents: "

result:
    .asciiz "\nResult: "

comma:
    .asciiz ", "

prompt:
    .asciiz "\nSelect a function (countvalues=0, findsecondlargest=1, countbs=2, exit=3): "

jTable:
    .word   F0, F1, F2, F3

arr:
    .word   0x00000000

len:
    .word   0x00000000

# myArray declaration allocates & initializes a 16 integer array,
myArray:
        .word 1 56 1 80 3 4 0 81 2 5 4 84 1 23 7 9

#Text Area (i.e. instructions)
.text

main:
    # Display the welcome message;  lui and ori constants are obtained
    #  manually from the global symbol table.  One will surely appreciate
    #  the la pseudoinstruction after this exercise.
    ori     $v0, $0, 4          
    la     $a0, welcome
    syscall

input:
    # ask which function to test
    ori     $v0, $0, 4          
    la      $a0, prompt
    syscall
    # read number
    ori     $v0, $0, 5
    syscall
    add     $s7, $v0, $0
    
    # print out which they are going to call
    ori     $v0, $0, 1          
    add     $a0, $s7, $0
    syscall

    # Implment a jump table to get to the function call
    sltiu   $t7, $s7, 5     # check for valid input
    beq     $t7, $0, input  # invalid input goes to prompt
    sll     $s7, $s7, 2     # multiply by 4 to increment PC on word boundary
    la      $t7, jTable     # load jump table
    add     $t7, $t7, $s7   # add the offset
    lw      $t7, 0($t7)     # load the address
    j       $t7             # jump

# count values
F0:
    #read in the index
    ori $v0, $0, 4
    la 	$a0, value
    syscall
    # read in number
    ori     $v0, $0, 5
    syscall


    add $a2, $v0, $0
    la $a0, myArray
    addi $a1, $0, 16
    
    jal countInstances

    # move and protect result
    add $s0, $v0, $0

    # print address
    ori     $v0, $0, 4
    la      $a0, result
    syscall
    ori     $v0, $0, 1
    or      $a0, $0, $s0
    syscall

    # Return to input
    j       input

# getvalue
F1:
    la $a0, myArray
    addi $a1, $0, 16
    
    jal secondLargest

    # move and protect result
    add $s0, $v0, $0

    # print address
    ori     $v0, $0, 4
    la      $a0, result
    syscall
    ori     $v0, $0, 1
    or      $a0, $0, $s0
    syscall

    # Return to input
    j       input

F2:
    la $a0, myArray
    addi $a1, $0, 16
    
    jal countBs

    # move and protect result
    add $s0, $v0, $0

    # print address
    ori     $v0, $0, 4
    la      $a0, result
    syscall
    ori     $v0, $0, 1
    or      $a0, $0, $s0
    syscall

    # Return to input
    j       input
F3:
    # Exit
    ori     $v0, $0, 10
    syscall

# COPYFROMHERE do not remove this line

countInstances:
	# i=$t0 count=$t1 $s0=array[i]
	or $t0, $0, $0
	or $t1, $0, $0
loop1:
 	slt $t2, $t0, $a1
	beq $t2, $0, endloop1
	lw $s0, 0($a0)
	bne $s0, $a2, skip
	addi $t1, $t1, 1
skip:
	addi $t0, $t0, 1
	addi $a0, $a0, 4
	j loop1

endloop1:
	or $v0, $t1, $0
	jr	$ra
	
	
	
	
secondLargest:
	# $t0=i  $s0=largest $s1=nextLargest 
	# $a0=*array $a1=length
	# $t1=array[0] $t2=array[1]
	lw $t0, 4($a0)
	
	ori $t3, $0, 2
	slt $t1, $a1, $t3
	beq	$t1, $0, normal
	sll $v0, $t1, 31
	jr 	$ra
	
normal:
	lw $t1, 0($a0)
	lw $t2, 4($a0)
	slt $t3, $t1, $t2
	beq, $t3, $0, skip_1
	or $s0, $t2, $0
	or $s1, $t1, $0
	
skip_1:
	or $s0, $t1, $0
	or $s1, $t2, $0
	
	ori $a0, $a0, 8
	ori $t0, $0, 2
loop2:	
	slt $t1, $t0, $a1
	beq $t1, $0, endloop2
	
	lw $t2, 0($a0)
	slt $t1, $t2, $s0	
	bne $t1, $0, elif
	or $s1, $s0, $0
	or $s0, $t2, $0
	j skip_2
	
elif:
	slt $t1, $s1, $t2
	beq $t1, $0, skip_2
	or $s1, $t2, $0 
	
skip_2:	
	addi $a0, $a0, 4
	addi $t0, $t0, 1
	j loop2
		
endloop2:
	or $v0, $s1, $0
	jr	$ra
	
countBs:
	jr      $ra


# xSpim Demo Program
#
#   CPE 315
#   fall 2001
#
# By: Dan Stearns
# Date:
# Modifications:
#       4/10/03 knico Tabbed code
#       4/10/03 knico Modified to use s registers instead of t registers
#
#
# declare global so programmer can see actual addresses.
.globl prompt
.globl largest
.globl smallest
#  Data Area - allocate and initialize variables
.data

	# TODO: Complete these declarations / initializations

prompt:
	.asciiz "Enter the next number:\n"

largest:
	.asciiz "Biggest:  "

smallest:
	.asciiz "\nSmallest:  "


#Text Area (i.e. instructions)
.text

main:

	# TODO: Write your code here
	# s0 is largest, s1 is samllest
	# $s3 store the number of input
	or $s3, $0, $0
	
	# $s4 store constant 4
	ori $s4, $0, 4
	
	#prompt and read the first integer into $s0 and $s1
	ori $v0, $0, 4
	la  $a0, prompt
	syscall
	ori $v0, $0, 5
	syscall
	or $s0, $0, $v0
	or $s1, $0, $v0
	addi $s3, $s3, 1

	#prompt the next number and compare it with largest and smallest 
loop:
	ori $v0, $0, 4
	la  $a0, prompt
	syscall
	ori $v0, $0, 5
	syscall
	slt $t0, $v0, $s0
	beq	$t0, $0, after1
	or $s0, $0, $v0
after1:
	slt $t0, $s1, $v0
	beq $t0, $0, after2
	or $s1, $0, $v0
after2:
	addi $s3, $s3, 1
	beq	$s3,$s4, endloop
	j loop
endloop:

	ori $v0, $0, 4
	la $a0, largest
	syscall
	ori $v0, $0, 1
	or $a0, $0, $s1
	syscall
	
	ori $v0, $0, 4
	la $a0, smallest
	syscall
	ori $v0, $0, 1
	or $a0, $0, $s0
	syscall


	# Exit
	ori     $v0, $0, 10
	syscall
	        

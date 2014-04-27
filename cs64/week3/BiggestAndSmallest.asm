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
.globl biggest
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

	#prompt and read the first integer into $s0
	ori $v0, $0, 4
	la  $a0, $0, prompt
	syscall
	ori $v0, $0, 5
	ori $s0, $0, $v0

	#prompt the second number and compare it with the first number
	ori $v0, $0, 4
	la  $a0, $0, prompt
	syscall
	sub $t0, $s0, $v0
	srl $t0, $t0, 31
	bne $t0, $0, branch




	# Exit
	ori     $v0, $0, 10
	syscall
	        

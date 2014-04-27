
.globl prompt

.data


prompt:
			.asciiz "Enter the next number:\n"



.text

main:

	ori $v0, $0, 4
	la  $a0, prompt
	syscall
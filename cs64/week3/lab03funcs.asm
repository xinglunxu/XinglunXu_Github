.text

# COPYFROMHERE

storevalues:

        # TODO: translate each line into mips
        # use only regs $v0-$v1, $t0-$t7, $a0-$a3
        # you may assume nothing about their starting values

        # globalA = 6
        la $t0, globalA
        ori $t1, $0, 6
        sw $t1, 0($t0)

        # globalB = 6
		la $t0, globalB
        ori $t1, $0, 6
        sw $t1, 0($t0)
        
        # globalC = 30
		la $t0, globalC
        ori $t1, $0, 30
        sw $t1, 0($t0)
		
        # globalD = 30
        la $t0, globalD
        ori $t1, $0, 30
        sw $t1, 0($t0)

        # do not remove this line
        jr $ra

storeregvalues:
        # TODO:  In this case, instead of storing constants,
        # we are storing register values into the variables
        # use only regs $v0-$v1, $t0-$t7,
        # you may assume that $a0-$a3 have already been set

        # globalA = $a3
		la $t0, globalA
        sw $a3, 0($t0)
        
        # globalB = $a2
		la $t0, globalB
        sw $a2, 0($t0)
        
        # globalC = $a1
		la $t0, globalC
        sw $a1, 0($t0)
        
        # globalD = $a0
		la $t0, globalD
        sw $a0, 0($t0)
		
        # do not remove this line
        jr $ra


copyvalues:
        # TODO: translate each line into mips
        # use only regs $v0-$v1, $t0-$t7, $a0-$a3
        # you may assume nothing about their starting values
        # globalA = globalB

        # globalD = globalC

        # do not remove this line
        jr $ra


operations:
        # TODO: translate each line into mips
        # use only regs $v0-$v1, $t0-$t7, $a0-$a3
        # you may assume nothing about their starting values


        # globalA = globalC + globalB

        # globalD = globalB + globalA

        # do not remove this line
        jr $ra


arrays:
        # TODO: translate each line into mips
        # use only regs $v0-$v1, $t0-$t7, $a2-$a3
        # you may assume nothing about their starting values
        # $a0 and $a1 have already been set for you

        #myArray[0] = $a0

        #myArray[1] = $a1

        #myArray[2] = globalD

        #globalB = myArray[3]

        # do not remove this line
        jr $ra

arraycalcs:
        # TODO: translate each line into mips
        # use only regs $v0-$v1, $t0-$t7, $a0-$a3
        # you may assume nothing about their starting values

        # globalB = myArray[0] + myArray[5]

        # do not remove this line
        jr $ra


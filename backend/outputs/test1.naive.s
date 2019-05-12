.data
sum0: .word 0
i0: .word 0
X: .word 10:100
Y: .word 10:100
.text
main:
addiu $sp,$sp,-36
sw $ra,4($sp)
li $t0,0
sw $t0,i0
loop_label0:
lw $t0,8($sp)
bgt $t0,99,loop_label1
lw $s0,8($sp)
la $t0,X
add $s0,$s0,$s0
add $s0,$s0,$s0
add $t0,$t0,$s0
lw $t1,0($t0)
sw $t1,12($sp)
lw $s0,8($sp)
la $t0,Y
add $s0,$s0,$s0
add $s0,$s0,$s0
add $t0,$t0,$s0
lw $t1,0($t0)
sw $t1,16($sp)
lw $t1,12($sp)
lw $t2,16($sp)
mulo $t0,$t1,$t2
sw $t0,20($sp)
lw $t1,20($sp)
lw $t2,sum0
add $t0,$t2,$t1
sw $t0,28($sp)
lw $t0,28($sp)
sw $t0,sum0
lw $t1,8($sp)
addi $t1,$t1,1
sw $t1,8($sp)
j loop_label0
loop_label1:
lw $a0,sum0
li $v0,1
syscall
lw $ra,4($sp)
addiu $sp,$sp,36
jr $ra


.data
.text
main:
addiu $sp,$sp,-100
sw $ra,4($sp)
li $t0,0
sw $t0,76($sp)
loop_label0:
lw $t0,76($sp)
sw $t0,76($sp)
bgt $t0,99,loop_label1
array_load, $t0, X, i0
array_load, $t1, Y, i0
lw $t1,80($sp)
lw $t2,84($sp)
mulo $t0,$t1,$t2
lw $t4,92($sp)
add $t3,$t4,$t0
move $t4,$t3
add $t5,$t5,1
sw $t4,92($sp)
sw $t5,76($sp)
j loop_label0
loop_label1:
lw $a0,92($sp)
li $v0,1
syscall
lw $ra,4($sp)
addiu $sp,$sp,100
jr $ra


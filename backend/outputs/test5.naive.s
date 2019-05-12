.data
b0: .word 0
a0: .word 0
.text
main:
addiu $sp,$sp,-28
sw $ra,4($sp)
li $t0,0
sw $t0,16($sp)
lw $t0,12($sp)
bne $t0,0,if_label0
li $t0,1
sw $t0,16($sp)
if_label0:
lw $t0,16($sp)
beq $t0,0,if_label1
lw $t1,8($sp)
addi $t0,$t1,2
sw $t0,20($sp)
lw $t0,20($sp)
sw $t0,a0
j if_label2
if_label1:
li $t0,2
sw $t0,a0
if_label2:
lw $a0,a0
li $v0,1
syscall
lw $ra,4($sp)
addiu $sp,$sp,28
jr $ra


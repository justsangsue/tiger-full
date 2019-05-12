.data
.text
main:
addiu $sp,$sp,-76
sw $ra,4($sp)
lw $t1,32($sp)
add $t0,$t1,0
sw $t0,52($sp)
lw $t0,52($sp)
move $t1,$t0
sw $t1,32($sp)
lw $t1,36($sp)
add $t0,$t1,1
move $t1,$t0
lw $t3,40($sp)
add $t2,$t3,2
move $t3,$t2
sw $t3,40($sp)
lw $t4,44($sp)
add $t3,$t4,3
move $t4,$t3
lw $t6,48($sp)
add $t5,$t6,4
move $t6,$t5
li $t7,0
sw $t7,72($sp)
sw $t6,48($sp)
sw $t4,44($sp)
sw $t1,36($sp)
beq $t1,0,if_label0
li $t0,1
sw $t0,72($sp)
if_label0:
lw $t0,72($sp)
beq $t0,0,if_label1
li $t0,0
li $t1,1
li $t2,2
sw $t2,40($sp)
sw $t1,36($sp)
sw $t0,32($sp)
j if_label2
if_label1:
li $t0,3
li $t1,4
sw $t1,48($sp)
sw $t0,44($sp)
if_label2:
lw $a0,32($sp)
li $v0,1
syscall
lw $a0,36($sp)
li $v0,1
syscall
lw $a0,40($sp)
li $v0,1
syscall
lw $a0,44($sp)
li $v0,1
syscall
lw $a0,48($sp)
li $v0,1
syscall
lw $ra,4($sp)
addiu $sp,$sp,76
jr $ra


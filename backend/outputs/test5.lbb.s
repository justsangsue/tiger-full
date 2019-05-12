.data
.text
main:
addiu $sp,$sp,-124
sw $ra,4($sp)
li $t0,0
lw $t1,120($sp)
lw $t2,116($sp)
sw $t0,80($sp)
sw $t2,116($sp)
bne $t1,$t2,if_label0
li $t0,1
sw $t0,80($sp)
if_label0:
lw $t0,80($sp)
beq $t0,0,if_label1
lw $t1,116($sp)
add $t0,$t1,2
move $t2,$t0
sw $t2,120($sp)
j if_label2
if_label1:
li $t0,2
sw $t0,120($sp)
if_label2:
lw $a0,120($sp)
li $v0,1
syscall
lw $ra,4($sp)
addiu $sp,$sp,124
jr $ra


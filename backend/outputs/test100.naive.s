.data
E0: .word 0
D0: .word 0
C0: .word 0
B0: .word 0
A0: .word 0
.text
li $t0,0
sw $t0,16($sp)
li $t0,0
sw $t0,12($sp)
li $t0,0
sw $t0,8($sp)
li $t0,0
sw $t0,4($sp)
li $t0,0
sw $t0,0($sp)
j main
main:
addiu $sp,$sp,-52
sw $ra,48($sp)
lw $t0,20($sp)
addi $t0,$t1,0
sw $t0,20($sp)
lw $t0,20($sp)
sw $t0,0($sp)
lw $t0,24($sp)
addi $t0,$t1,1
sw $t0,24($sp)
lw $t0,24($sp)
sw $t0,4($sp)
lw $t0,28($sp)
addi $t0,$t1,2
sw $t0,28($sp)
lw $t0,28($sp)
sw $t0,8($sp)
lw $t0,32($sp)
addi $t0,$t1,3
sw $t0,32($sp)
lw $t0,32($sp)
sw $t0,12($sp)
lw $t0,36($sp)
addi $t0,$t1,4
sw $t0,36($sp)
lw $t0,36($sp)
sw $t0,16($sp)
li $t0,0
sw $t0,40($sp)
lw $t0,4($sp)
beq $t0,0,if_label0
li $t1,1
sw $t1,40($sp)
if_label0:
lw $t1,40($sp)
beq $t1,0,if_label1
li $t1,0
sw $t1,0($sp)
li $t1,1
sw $t1,4($sp)
li $t1,2
sw $t1,8($sp)
j if_label2
if_label1:
li $t1,3
sw $t1,12($sp)
li $t1,4
sw $t1,16($sp)
if_label2:
lw $a0,0($sp)
li $v0, 1
syscall
lw $a0,4($sp)
li $v0, 1
syscall
lw $a0,8($sp)
li $v0, 1
syscall
lw $a0,12($sp)
li $v0, 1
syscall
lw $a0,16($sp)
li $v0, 1
syscall
lw $ra,48($sp)
addiu $sp,$sp,52
jr $ra


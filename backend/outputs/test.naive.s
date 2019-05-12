.data
E0: .word 0
D0: .word 0
C0: .word 0
B0: .word 0
A0: .word 0
.text
main:
addiu $sp,$sp,-56
sw $ra,4($sp)
lw $t1,8($sp)
addi $t0,$t1,0
sw $t0,28($sp)
lw $t0,28($sp)
sw $t0,A0
lw $t1,12($sp)
addi $t0,$t1,1
sw $t0,32($sp)
lw $t0,32($sp)
sw $t0,B0
lw $t1,16($sp)
addi $t0,$t1,2
sw $t0,36($sp)
lw $t0,36($sp)
sw $t0,C0
lw $t1,20($sp)
addi $t0,$t1,3
sw $t0,40($sp)
lw $t0,40($sp)
sw $t0,D0
lw $t1,24($sp)
addi $t0,$t1,4
sw $t0,44($sp)
lw $t0,44($sp)
sw $t0,E0
li $t0,0
sw $t0,48($sp)
lw $t0,12($sp)
beq $t0,0,if_label0
li $t0,1
sw $t0,48($sp)
if_label0:
lw $t0,48($sp)
beq $t0,0,if_label1
li $t0,0
sw $t0,A0
li $t0,1
sw $t0,B0
li $t0,2
sw $t0,C0
j if_label2
if_label1:
li $t0,3
sw $t0,D0
li $t0,4
sw $t0,E0
if_label2:
lw $a0,A0
li $v0,1
syscall
lw $a0,B0
li $v0,1
syscall
lw $a0,C0
li $v0,1
syscall
lw $a0,D0
li $v0,1
syscall
lw $a0,E0
li $v0,1
syscall
lw $ra,4($sp)
addiu $sp,$sp,56
jr $ra


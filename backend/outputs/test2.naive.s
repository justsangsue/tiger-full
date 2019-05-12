.data
.text
print:
addiu $sp,$sp,-16
sw $ra,4($sp)
sw $a0,8($sp)
lw $a0,8($sp)
li $v0,1
syscall
lw $ra,4($sp)
addiu $sp,$sp,16
jr $ra
main:
addiu $sp,$sp,-16
sw $ra,4($sp)
li $a0,5
jal print
lw $ra,4($sp)
addiu $sp,$sp,16
jr $ra


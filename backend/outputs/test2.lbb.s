.data
.text
print:
addiu $sp,$sp,-104
sw $ra,4($sp)
sw $a0,100($sp)
lw $a0,100($sp)
li $v0,1
syscall
lw $ra,4($sp)
addiu $sp,$sp,104
jr $ra
main:
addiu $sp,$sp,-104
sw $ra,4($sp)
li $a0,5
jal print
lw $ra,4($sp)
addiu $sp,$sp,104
jr $ra


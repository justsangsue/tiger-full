.data
float0fhvbakj: .double 99.9
.text
print:
addiu $sp,$sp,-16
sw $ra,4($sp)
mtc1 $a0,$f12
li $v0,3
syscall
lw $ra,4($sp)
addiu $sp,$sp,16
jr $ra
main:
addiu $sp,$sp,-16
sw $ra,4($sp)
ldc1 $f12,float0fhvbakj
mfc1 $a0,$f12
jal print
lw $ra,4($sp)
addiu $sp,$sp,16
jr $ra


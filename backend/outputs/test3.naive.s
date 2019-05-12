.data
float0fhvbakj: .double 1.0
Y0: .double 0.0
X0: .double 0.0
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
addiu $sp,$sp,-24
sw $ra,4($sp)
li $a0,5
jal print
ldc1 $f0,float0fhvbakj
sdc1 $f0,X0
lw $ra,4($sp)
addiu $sp,$sp,24
jr $ra


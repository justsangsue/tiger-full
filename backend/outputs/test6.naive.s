.data
float0fhvbakj: .double 3.14
.text
main:
addiu $sp,$sp,-20
sw $ra,4($sp)
li $t0,42
sw $t0,8($sp)
ldc1 $f0,float0fhvbakj
sdc1 $f0,16($sp)
lw $a0,8($sp)
li $v0,1
syscall
lw $ra,4($sp)
addiu $sp,$sp,20
jr $ra


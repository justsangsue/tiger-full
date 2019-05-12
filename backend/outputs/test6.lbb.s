.data
float0fhvbakj: .double 3.14
.text
main:
addiu $sp,$sp,-132
sw $ra,4($sp)
li $t0,42
ldc1 $f0,float0fhvbakj
move $a0,$t0
li $v0,1
syscall
lw $ra,4($sp)
addiu $sp,$sp,132
jr $ra


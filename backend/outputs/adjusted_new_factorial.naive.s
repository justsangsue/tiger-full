.data
r_st_1_0: .word 1
.text

main:
addiu $sp,$sp,-24
sw $ra,4($sp)
li $t0,5
sw $t0,8($sp)
lw $a0,8($sp)
jal fact_st_1_0
sw $v0,12($sp)
lw $t1,12($sp)
sw $t1,20($sp)
lw $t0,r_st_1_0
sw $t0,16($sp)
lw $a0,16($sp)
li $v0,1
syscall
lw $ra,4($sp)
addiu $sp,$sp,24
jr $ra

fact_st_1_0:
addiu $sp,$sp,-60
sw $ra,4($sp)
li $t0,0
sw $t0,36($sp)
sw $a0,24($sp)
li $t0,1
sw $t0,28($sp)
li $t0,1
sw $t0,32($sp)
lw $t0,24($sp)
lw $t1,28($sp)
bne $t0,$t1,cond_0_stz_stf_fact_2_0
j cond_1_after_stf_fact_2_0
cond_0_stz_stf_fact_2_0:
li $t1,0
sw $t1,32($sp)
cond_1_after_stf_fact_2_0:
lw $t1,32($sp)
lw $t0,36($sp)
beq $t1,$t0,if_after2__stf_fact_2_0
li $t0,1
sw $t0,20($sp)
lw $ra,4($sp)
lw $v0,20($sp)
addiu $sp,$sp,60
jr $ra
if_after2__stf_fact_2_0:
sw $a0,44($sp)
li $t0,1
sw $t0,48($sp)
lw $t0,52($sp)
lw $t1,44($sp)
lw $t2,48($sp)
sub $t0,$t1,$t2
sw $t0,52($sp)
lw $a0,52($sp)
jal fact_st_1_0
sw $v0,8($sp)
lw $t2,8($sp)
sw $t2,60($sp)
sw $a0,12($sp)
lw $t0,r_st_1_0
sw $t0,16($sp)
lw $t0,40($sp)
lw $t2,12($sp)
lw $t1,16($sp)
mulo $t0,$t2,$t1
sw $t0,40($sp)
lw $ra,4($sp)
lw $v0,40($sp)
addiu $sp,$sp,60
jr $ra
li $t0,1
sw $t0,20($sp)

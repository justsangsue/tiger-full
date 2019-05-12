.data
r_st_1_0: .word 1
.text
fact_st_1_0:
addiu $sp,$sp,-168
sw $ra,4($sp)
li $t0,0
move $t1,$a0
li $t2,1
li $t3,1
sw $t3,144($sp)
sw $t0,148($sp)
bne $t1,$t2,cond_0_stz_stf_fact_2_0
j cond_1_after_stf_fact_2_0
cond_0_stz_stf_fact_2_0:
li $t0,0
sw $t0,144($sp)
cond_1_after_stf_fact_2_0:
lw $t0,144($sp)
lw $t1,148($sp)
beq $t0,$t1,if_after2__stf_fact_2_0
li $t0,1
lw $ra,4($sp)
move $v0,$t0
addiu $sp,$sp,168
jr $ra
if_after2__stf_fact_2_0:
move $t0,$a0
li $t1,1
sw $t1,160($sp)
lw $t2,160($sp)
sub $t1,$t0,$t2
sw $t1,164($sp)
sw $a0,24($sp)
lw $a0,164($sp)
jal fact_st_1_0
sw $v0,132($sp)
lw $a0,24($sp)
lw $t1,132($sp)
sw $t1,r_st_1_0
move $t2,$a0
lw $t3,r_st_1_0
mulo $t4,$t2,$t3
lw $ra,4($sp)
move $v0,$t4
addiu $sp,$sp,168
jr $ra
main:
addiu $sp,$sp,-168
sw $ra,4($sp)
li $t0,5
sw $a0,24($sp)
move $a0,$t0
jal fact_st_1_0
sw $v0,12($sp)
lw $a0,24($sp)
lw $t1,12($sp)
sw $t1,r_st_1_0
lw $t2,r_st_1_0
move $a0,$t2
li $v0,1
syscall
lw $ra,4($sp)
addiu $sp,$sp,168
jr $ra


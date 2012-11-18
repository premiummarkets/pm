values SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY('derby.locks.waitTimeout');
values SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY('derby.locks.deadlockTimeout');
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.locks.deadlockTimeout', '120');
values SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY('derby.locks.deadlockTimeout');
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.locks.waitTimeout','240');
values SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY('derby.locks.waitTimeout');
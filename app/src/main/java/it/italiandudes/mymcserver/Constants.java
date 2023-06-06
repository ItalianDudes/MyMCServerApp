package it.italiandudes.mymcserver;

public final class Constants {
    public final static class Connectivity{
        public static final int MAX_PORT_NUM = 65535;
        public static final String HTTP = "http://";

        public static final String LOGIN = "login";
        public static final String COMMAND = "command";
        public static final String STATS = "stats";
        public static final int THREAD_SLEEP_INFO = 10000;
    }

    public final static class Protocol{
        public static final class HTTP_Headers{
            public static final String MMCS_USERNAME = "MMCS-Username";
            public static final String MMCS_PWD = "MMCS-Password";
            public static final String RETURN_CODE = "return-code";
            public static final String TOKEN = "token";
            public static final String MMCS_COMMAND = "MMCS-Command";
            public static final String MMCS_TOKEN = "MMCS-Token";
            public static final String COMMAND_OUTPUT = "command-output";
            public static final String SYSTEM_CPU_LOAD = "system-cpu-load";
            public static final String FREE_MEMORY = "free-memory";
            public static final String TOTAL_MEMORY = "total-memory";
            public static final String PROCESS_CPU_LOAD = "process-cpu-load";
            public static final String COMMITTED_VIRTUAL_MEMORY = "committed-virtual-memory";
            public static final String PLAYER_LIST = "player-list";
            public static final String ADDONS_LIST = "addons-list";
            public static final String NAME = "name";
            public static final String ONLINE = "online";
            public static final String ENABLED = "enabled";
        }

        public static final class General{
            public static final int MAX_USERNAME_MINECRAFT_LENGTH = 16;
        }

        public final static class ReturnCodes{
            public static final int HTTP_OK = 200;
        }
    }

    public final static class Log{
        public static final String TAG = "MyMCServerApp-Logger";
    }

    public final static class CryptAlgorithms{
        public static final String SHA3_512 = "sha-512";
    }
}

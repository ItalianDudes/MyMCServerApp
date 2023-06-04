package it.italiandudes.mymcserver;

public final class Constants {
    public final static class Connectivity{
        public static final int MAX_PORT_NUM = 65535;
        public static final String HTTP = "http://";

        public static final String LOGIN = "login";
        public static final String COMMAND = "command";
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

package com.example.votingsystem.config;

public class Constant {

    public static class ResponseCode {
        public static class Success {
            public static final int code = 0;
            public static final String msg = "Success";
        }

        public static class NotAuthenticated {
            public static final int code = 0;
            public static final String msg = "Success";
        }

        public static class Validation {
            public static final int code = 400;
            public static final String msg = "Validation Error";
        }

        public static class UserNotFound {
            public static final int code = -100;
            public static final String msg = "User is not Found";
        }

        public static class UserAlreadyExists {
            public static final int code = -101;
            public static final String msg = "User already exists";
        }

        public static class CandidateNotFound {
            public static final int code = -110;
            public static final String msg = "Candidate with ID Not Found";
        }

        public static class CandidateAlreadyExists {
            public static final int code = -111;
            public static final String msg = "Candidate with name already exists";
        }

        public static class VoteNotFound {
            public static final int code = -120;
            public static final String msg = "Vote with ID Not Found";
        }

        public static class VoteAlreadyExists {
            public static final int code = -121;
            public static final String msg = "Vote with national ID already exists";
        }

        public static class VoteAlreadyConfirmed {
            public static final int code = -130;
            public static final String msg = "Vote has already benn confirmed";
        }




    }
}


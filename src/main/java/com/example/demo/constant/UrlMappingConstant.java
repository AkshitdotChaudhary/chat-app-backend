package com.example.demo.constant;

public interface UrlMappingConstant
{
    /* =========================
       API BASE
       ========================= */
    String API_BASE                 = "/api";
    /* =========================
       AUTH APIs
       ========================= */
    String AUTH_BASE                = API_BASE + "/auth";
    String LOGIN                    = AUTH_BASE + "/login";
    String REGISTER                 = AUTH_BASE + "/register";
    String LOGOUT                   = AUTH_BASE + "/logout";
    /* =========================
       USER APIs
       ========================= */
    String USERS_BASE               = API_BASE + "/users";
    String CURRENT_USER             = USERS_BASE + "/me";
    String SEARCH_USERS             = USERS_BASE + "/search";
    String USER_STATUS              = USERS_BASE + "/{id}/status";
    String USER_LAST_SEEN           = USERS_BASE + "/last-seen";
    /* =========================
       CONVERSATION APIs
       ========================= */
    String CONVERSATION_BASE        = API_BASE + "/conversations";
    String CREATE_CONVERSATION      = CONVERSATION_BASE;
    String GET_CONVERSATIONS        = CONVERSATION_BASE;
    String GET_CONVERSATION_DETAILS = CONVERSATION_BASE + "/{conversationId}";
    String DELETE_CONVERSATION      = CONVERSATION_BASE + "/{conversationId}";
    /* ---- Group specific ---- */
    String UPDATE_GROUP_TITLE       = CONVERSATION_BASE + "/{conversationId}/title";
    String ADD_MEMBERS              = CONVERSATION_BASE + "/{conversationId}/members";
    String REMOVE_MEMBER            = CONVERSATION_BASE + "/{conversationId}/members/{userId}";
    String LEAVE_GROUP              = CONVERSATION_BASE + "/{conversationId}/leave";
    String GET_MEMBERS              = CONVERSATION_BASE + "/{conversationId}/members";
    /* =========================
       MESSAGE APIs
       ========================= */
    String MESSAGE_BASE             = API_BASE + "/messages";
    String GET_MESSAGES             = CONVERSATION_BASE + "/{conversationId}/messages";
    String DELETE_MESSAGE           = MESSAGE_BASE + "/{messageId}";
    String EDIT_MESSAGE             = MESSAGE_BASE + "/{messageId}";
    /* =========================
       MESSAGE STATUS APIs
       ========================= */
    String MARK_AS_READ             = CONVERSATION_BASE + "/{conversationId}/read";
    String UNREAD_COUNT             = CONVERSATION_BASE + "/{conversationId}/unread-count";
    /* =========================
       ATTACHMENTS
       ========================= */
    String ATTACHMENT_BASE          = API_BASE + "/attachments";
    String UPLOAD_ATTACHMENT        = MESSAGE_BASE + "/{messageId}/attachments";
    String DOWNLOAD_ATTACHMENT      = ATTACHMENT_BASE + "/{attachmentId}";
    /* =========================
       PRIVACY & MODERATION
       ========================= */
    String BLOCK_USER               = USERS_BASE + "/{userId}/block";
    String UNBLOCK_USER             = USERS_BASE + "/{userId}/block";
    String MUTE_CONVERSATION        = CONVERSATION_BASE + "/{conversationId}/mute";
    /* =========================
       WEBSOCKET
       ========================= */
    String WS_ENDPOINT              = "/ws-chat";
    /* ---- STOMP Destinations ---- */
    // Client → Server (used in @MessageMapping)
    String WS_SEND_MESSAGE          = "/chat.send/{conversationId}";
    String WS_TYPING                = "/chat.typing/{conversationId}";
    // Server → Client (used in convertAndSend / subscribe)
    String WS_TOPIC_PREFIX          = "/topic";
    String WS_CONVERSATION_TOPIC    = WS_TOPIC_PREFIX + "/conversation/";
    String WS_TYPING_TOPIC          = WS_TOPIC_PREFIX + "/conversation/";
}

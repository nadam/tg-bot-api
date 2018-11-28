package se.anyro.tgbotapi.utils;

import se.anyro.tgbotapi.types.MessageEntity;
import se.anyro.tgbotapi.types.MessageEntity.Type;

public class Markdown {

    /**
     * Escape text for markdown format
     */
    public static String escape(String text) {
        return text.replace("_", "\\_").replace("*", "\\*").replace("`", "\\`").replace("[", "\\[");
    }

    /**
     * Restore markdown escape characters from the entities of a Message
     */
    public static String applyFromEntities(String text, MessageEntity[] entities) {
        if (entities == null || entities.length == 0) {
            return text;
        }
        int pos = 0;
        int entityStart = entities[0].offset;
        StringBuilder builder = new StringBuilder(text.substring(0, entityStart));
        for (MessageEntity entity : entities) {

            // Plain text before the entity
            String plainText = text.substring(pos, pos = entity.offset);
            builder.append(escape(plainText));

            // Start tag
            String startTag = getStartTag(entity.getType());
            if (startTag != null) {
                builder.append(startTag);
            }

            // Text inside entity
            String entityText = text.substring(pos, pos += entity.length);
            builder.append(escape(entityText));

            // End tag
            String endTag = getEndTag(entity.getType());
            if (endTag != null) {
                builder.append(endTag);
            }

            // Extra for text_link and text_mention
            if (entity.url != null) {
                builder.append('(').append(entity.url).append(')');
            } else if (entity.user != null) {
                builder.append('(').append(entity.user.getUrl()).append(')');
            }
        }

        // Final plain text after the last tag
        builder.append(text.substring(pos, text.length()));

        return builder.toString();
    }

    private static String getStartTag(Type entityType) {
        switch (entityType) {
        case BOLD:
            return "*";
        case CODE:
            return "`";
        case ITALIC:
            return "_";
        case PRE:
            return "```";
        case TEXT_LINK:
        case TEXT_MENTION:
            return "[";
        default:
            return null;
        }
    }

    private static String getEndTag(Type entityType) {
        switch (entityType) {
        case TEXT_LINK:
        case TEXT_MENTION:
            return "]";
        default:
            return getStartTag(entityType);
        }
    }
}

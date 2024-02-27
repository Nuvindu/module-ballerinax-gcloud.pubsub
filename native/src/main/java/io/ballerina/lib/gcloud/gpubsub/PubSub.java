package io.ballerina.lib.gcloud.gpubsub;

import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.pubsub.v1.ProjectName;
import com.google.pubsub.v1.Topic;
import com.google.pubsub.v1.TopicName;
import io.ballerina.runtime.api.utils.StringUtils;
import io.ballerina.runtime.api.values.BString;

import java.io.IOException;

public class PubSub {

    private PubSub() {}

    public static BString listTopics(BString id) throws IOException {
        String projectId = id.getValue();
        try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
            ProjectName projectName = ProjectName.of(projectId);
            for (Topic topic : topicAdminClient.listTopics(projectName).iterateAll()) {
                return StringUtils.fromString(topic.getName());
            }
        }
        return null;
    }

    public static BString createTopic(String projectId, String topicId) throws IOException {
        try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
            TopicName topicName = TopicName.of(projectId, topicId);
            Topic topic = topicAdminClient.createTopic(topicName);
            return StringUtils.fromString(topic.getName());
        }
    }
}

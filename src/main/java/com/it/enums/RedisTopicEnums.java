package com.it.enums;


public enum RedisTopicEnums {

    /**
     * redis主题名称定义 需要与发布者一致
     *
     */
    TOPIC_DISCOVERY("topic:discovery", "设备发现变更Topic"),

    TOPIC_HEALTHY("topic:healthy", "健康扫描的设备Topic"),

    TOPIC_SETTINGS("topic:settings",  "配置扫描变更的设备Topic"),

    TOPIC_DISCOVER("topic:discover", "发现设备Topic");

    /**
     * 主题名称
     */
    private String topic;

    /**
     * 描述
     */
    private String description;

    RedisTopicEnums(String topic, String description) {
        this.topic = topic;
        this.description = description;
    }


    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }


}

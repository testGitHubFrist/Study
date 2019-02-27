package com.zsc.study.kafka;

public enum EnumKafkaTopic {
    test_topic("test","测试主题");


    private String topic;

    private String name;

    EnumKafkaTopic(String topic, String name) {
        this.topic = topic;
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

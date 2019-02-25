package com.zsc.study.kafka.consume;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;

public class SaveOffsetsOnRebalance implements ConsumerRebalanceListener {
       private Consumer<?,?> consumer;

       public SaveOffsetsOnRebalance(Consumer<?,?> consumer) {
           this.consumer = consumer;
       }

       public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
           // save the offsets in an external store using some custom code not described here
           for(TopicPartition partition: partitions)
              saveOffsetInExternalStore(consumer.position(partition));
       }

       public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
           // read the offsets from an external store using some custom code not described here
           for(TopicPartition partition: partitions)
              consumer.seek(partition, readOffsetFromExternalStore(partition));
       }

       public void saveOffsetInExternalStore(long partition){
           consumer.commitSync();
       }

       public long readOffsetFromExternalStore(TopicPartition partition){
           return  5;
       }
   }
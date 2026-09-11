package net.spfwork.forum.domain;

import java.time.LocalDateTime;

/*
CREATE TABLE `category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '分类名称',
  `weight` int(11) DEFAULT NULL COMMENT '权重，数字越大权重越高',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4
* */
public class Category {
    private int id;
    private String name;
    private int weight;
    private LocalDateTime createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", createTime=" + createTime +
                '}';
    }
}

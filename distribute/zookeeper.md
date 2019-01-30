# Zookeeper学习笔记

[TOC]

## 介绍

保障强一致性，有序性，持久性

实现通用的同步原语的能力

提供一种简单的并发处理机制

zookeeper管理应用协作的关键数据，将应用数据与协同数据独立开

消息延迟，处理器性能，时钟偏移

主从模式三个关键问题：主节点崩溃，从节点崩溃，通信故障

主节点失效新的节点需要从某个地方恢复到前主崩溃前的状态， 另外由于消息延迟或网络造成系统中两个或多个部分开始独立工作，这称为脑裂





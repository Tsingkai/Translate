# translate

#### 介绍
eclipse 翻译插件
百度翻译

> + 该插件是参考GitHub项目：https://github.com/supernova-explosion/translate
> + 可能由于百度翻译API更新导致原项目无法使用，故进行开发本项目。
> + 本项目照抄了Preference部分，重写了翻译部分，重构了handler结构，细化了错误描述等。

#### 软件架构
软件架构说明
+ Eclipse 2020-06
+ Java version "1.8.0_311"

#### 参考项目
1. https://github.com/supernova-explosion/translate

#### 使用说明

1.  在[百度翻译API](https://api.fanyi.baidu.com/)申请 【通用文本翻译】，标准版的不收费哦，实际使用依据个人实际情况是否使用高级版
> 2022年8月1日起，通用翻译API标准版免费调用量调整为5万字符/月，高级版免费调用量调整为100万字符/月
2.  在[通用翻译API接入文档](https://api.fanyi.baidu.com/doc/21)中，【接入方式】一节中，将[通用翻译API HTTPS 地址](https://fanyi-api.baidu.com/api/trans/vip/translate)粘贴进设置页面。
> 设置项位于 eclipse内： 【Window】-> 【Preferences】->【Translate】中
3.	在[开发者信息](https://api.fanyi.baidu.com/manage/developer)中，找到【APP ID】、【密钥】填入设置页面的【APP_ID】、【SECRET_KEY】中，然后设置好源语言和目标语言
4.	设置完成后，eclipse代码编辑器中，【选中单词】->【右键】->【Translate】

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


kpt-to-backlog
==========

PJ振り返り手法のKPTをbacklogAPI(backlog4j)と連動して実施できるツールです。  
herokuページは[こちら](https://kpt-on-backlog.herokuapp.com/)  
![cap_14](https://user-images.githubusercontent.com/24874752/58574753-a7d73d00-827b-11e9-8daf-991d57ad0493.PNG)



目次
-----------------

  * [事前準備](#事前準備)
  * [操作手順・機能](#操作手順・機能)
  * [使用している技術](#使用している技術)

事前準備
------------

  * Backlogの「個人設定 > API」からAPIキーを発行してください。  
  ![cap_1](https://user-images.githubusercontent.com/24874752/58573104-fb478c00-8277-11e9-9240-df7d0dbb0acc.PNG)

  * KPTを実施したいプロジェクトのカテゴリに「KEEP」「PROBLEM」「TRY」を追加してください。  
 ![cap_10](https://user-images.githubusercontent.com/24874752/58573225-43ff4500-8278-11e9-8bec-970bb4a0d162.PNG)


操作手順・機能
------------
  * トップページから、APIKEY、SPACEKEY、PROJECTKEYを入力してサインインしてください。    
  ![cap_13](https://user-images.githubusercontent.com/24874752/58573581-051dbf00-8279-11e9-9933-897a22567687.PNG)

  * BOX内をクリックすることで、新規課題を設定できます。  
  ![cap_3](https://user-images.githubusercontent.com/24874752/58573258-524d6100-8278-11e9-9fb7-b49fe90037ca.PNG)  
  ![cap_4](https://user-images.githubusercontent.com/24874752/58573267-58dbd880-8278-11e9-8cd8-9a47be5a42a8.PNG)  
  ![cap_5](https://user-images.githubusercontent.com/24874752/58573281-5da08c80-8278-11e9-9aa0-52e1e52298dc.PNG)  
  ![cap_6](https://user-images.githubusercontent.com/24874752/58573299-65f8c780-8278-11e9-9be4-8fcf226663c7.PNG)

  * 既存の課題をクリックすることで、課題の更新ができます。  
  ![cap_7](https://user-images.githubusercontent.com/24874752/58573312-6e510280-8278-11e9-9809-c90189c5386b.PNG)

  * 既存の課題をD&Dすることで、同じく課題の更新ができます。この場合、更新フォームのカテゴリがドロップされたKPTになっています。  
  ![cap_8](https://user-images.githubusercontent.com/24874752/58573339-7ad55b00-8278-11e9-854f-659aed06e398.PNG)  
  ![cap_9](https://user-images.githubusercontent.com/24874752/58573353-8032a580-8278-11e9-98dc-fdb57dccb785.PNG)

  * 課題を「完了」させることで、KPT表から対象の課題がKPT-on-backlogから削除されます。(backlogには残ります)  
  ![cap_11](https://user-images.githubusercontent.com/24874752/58573466-bbcd6f80-8278-11e9-96a0-d32826e96d8e.PNG)  
  ![cap_12](https://user-images.githubusercontent.com/24874752/58573479-c12aba00-8278-11e9-84f0-2a1382595816.PNG)


使用している技術
-----

  * backlog4j
  * JDK 1.8
  * Spring Boot
  * Thymeleaf
  * jQuery


License
-------

Copyright &copy; 2019, knry0329

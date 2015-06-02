#Inazuma - 電

##概要

指定したデータをSparkで分解しカウントし、TOP10をD3.JSのBubble Chartで表示するプログラム。

NHKのつぶやビッグデータのインスパイアなソフトウェアです。

オフラインのデータをSparkで解析したい時のリファレンスコードを目指しています。


![pic](http://i.imgur.com/m2EPkHt.png)

##必要要件

* JDK 7+
* SBT
* Ruby

##Usage

sbt boot

``sbt``

boot inazuma

``run [data_file_path]``

or

``run [data_file_path] [kuromoji_dict_path]``

or 

``run [data_file_path] [kuromoji_dict_path] [rank_take_num]``

``run ./private/1433194505.txt ./dictionary/anime_2015_2Q.txt 20``

translate data csv->d3.json

``ruby csv2d3jsjson.rb data.csv > data.json``

open browser tubuyaki_bigdata.html

or mac osx shell

``open tubuyaki_bigdata.html``


### メインプログラム情報
 
#### inazuma

通常のテキストをパースする場合はこちら。

プログラム内部ではフィルタリングをしていません。

#### inazumaTwitter

Twitterのストリームを解析する場合はこれを使う。

日本語のみを抽出するようにフィルタリングしています。

## アニメ作品タイトルの辞書について

Project-ShangriLaの以下のツールを利用しています。

https://github.com/Project-ShangriLa/CreateDictionary

## 補足情報

### HDFSファイルを読み込ませたい時

sbtにHDFSのライブラリを追加してください。

### Sparkクラスタで動作させたい時

sbt assemblyでjarを作ってspark-submitで実行してください。

## 参考情報

### D3.js
* http://d3js.org/
* http://bl.ocks.org/mbostock/4063269
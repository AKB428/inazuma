#Inazuma - 電

##概要

指定したデータをSparkで分解しカウントし、TOP10をD3.JSのBubble Chartで表示するプログラム。

NHKのつぶやビッグデータのインスパイアなソフトウェアです。

オフラインのデータをSparkで解析したい時のリファレンスコードを目指しています。

##インストールに必要なソフト

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

translate data csv->d3.json

``ruby csv2d3jsjson.rb data.csv > data.json``

open browser tubuyaki_bigdata.html

or mac osx shell

``open tubuyaki_bigdata.html``


## 補足情報

### HDFSファイルを読み込ませたい時

sbtにHDFSのライブラリを追加してください。

### Sparkクラスタで動作させたい時

sbt assemblyでjarを作ってspark-submitで実行してください。

## 参考情報

### D3.js
* http://d3js.org/
* http://bl.ocks.org/mbostock/4063269
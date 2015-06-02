# 売りスレ解析

http://refugee-chan.mobi/urisure/

## データ取得

``curl -O http://refugee-chan.mobi/urisure/dat/1432824063.dat``

## 文字コード変換

``nkf -w 1432824063.dat > 1432824063.dat.utf8``

## 文章データだけ抽出
``ruby urisure_dat_extraction.rb 1432824063.dat.utf8 > 1432824063.txt``



![pic](http://i.imgur.com/IFg1VZD.png)



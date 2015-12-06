ref:http://katsu-tech.hatenablog.com/entry/2015/03/24/072848

wget http://dumps.wikimedia.org/jawiki/latest/jawiki-latest-all-titles-in-ns0.gz
gunzip jawiki-latest-all-titles-in-ns0.gz
grep -v '[!-/:-@≠\[-`{-~]' jawiki-latest-all-titles-in-ns0 | grep -v '^[0-9]' | egrep -v '^[ぁ-ん]{2}' | grep -v 'の一覧' | grep -v '曖昧さ回避' | awk '{ print length($1),$1; }' | grep -v '1[[:space:]]' | sed -r 's/.*[[:space:]](.*)/\1,\1,a,wiki/g' > user_dic.csv

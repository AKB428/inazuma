#ツイート文中の改行を削除

write_first_filename = ARGV[0] + ".tmp"

file_out = open(write_first_filename,"w")
file = open(ARGV[0],"r:UTF-8")
file.each_line do |line|
  line2 = line.strip
  #ツイート末尾の場合のみ改行付きで書き込み
  if line2[-1] == ',' then
    #末尾のカンマ削除
    file_out.puts(line2.chop)
  else
    file_out.print(line2)
  end
end
file.close
file_out.close

#ツイート中にカンマがある場合、レコードごと削除
file = open(write_first_filename,"r:UTF-8")
file.each_line do |line|
  #ツイート文中にカンマがない場合のみ書き込み
  if line.count(',') == 6 then
    av = line.split(",")
    puts av[2]
  elsif line.count(',') > 6 then
    av = line.split(",")
    length = av.length
    new_av = [av[0],av[1],av[2..(length-4)].join(" "),av[length-3],av[length-2],av[length-1]]
    puts new_av[2]
  end
end
file.close

File.unlink write_first_filename
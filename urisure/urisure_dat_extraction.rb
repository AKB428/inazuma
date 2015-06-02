open(ARGV[0]) {|file|
  while line = file.gets
    res_data = line.split("<>")

    if res_data.size > 3
      stage1 =  res_data[3].delete("<br>").delete("&gt;").delete(";&").delete("http://").delete("imu").delete("co")

      puts stage1.gsub('jp|imgur|https|com|jpeg|png|hp', ' ')
    end

  end
}

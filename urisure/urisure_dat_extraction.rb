open(ARGV[0]) {|file|
  while line = file.gets
    res_data = line.split("<>")

    if res_data.size > 3
      puts res_data[3]
    end

  end
}

class TwitterDataCleansing
  open(ARGV[0]) {|file|
    while line = file.gets
        puts line.gsub(/(全員|ふぁぼ|ファボ|定期|相互)/, ' ')
    end
  }
end


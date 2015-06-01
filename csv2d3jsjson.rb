require "json"

def build_children(title, value)
  children = {}
  children['name'] = title
  children['children'] = []
  children['children'][0] = {'name' =>  title, 'size' => value}
  children
end

def build_data(csv_data_map)
  data = {}
  data['name'] = 'flare'
  data['children'] = csv_data_map.map{|k, v| build_children(k,v)}
  JSON.dump(data)
end


csv_data_map = {}

open(ARGV[0]) {|file|
  while line = file.gets
    record = line.split(',')
    csv_data_map[record[0]] = record[1]
  end
}

puts build_data(csv_data_map)
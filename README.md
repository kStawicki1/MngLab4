<b> skrypt zamieniajacy 0 na O w kazdym polu "name" ClientEntityRepository</b>
<br>


clientEntityRepository.findAll().forEach(function(client) {
var name = client.getName();
name = name.replace(/0/g, 'O');
client.setName(name);
clientEntityRepository.save(client);
});


var Ajabshahar = Ajabshahar || {};
Ajabshahar.user = Ajabshahar.user || {};
Ajabshahar.user.FilterItem = function (names, category, id) {
    var self = {};
    self.names = names || [];
    self.category = category;
    self.id = id;
    return self;

};
var Ajabshahar = Ajabshahar || {};
Ajabshahar.user = Ajabshahar.user || {};
Ajabshahar.user.Sieve = function (filterCriteria) {
    var self = {};
    self.selectedFilters = [];

    self.filterCriteria = filterCriteria;

    var substringAfter = function (text, fromChar) {
        return text.indexOf(fromChar) >= 0 ? text.substring(text.indexOf(fromChar) + 1) : "";
    };

    var substringUpto = function (text, char) {
        return text.substring(0, text.indexOf(char));
    };

    var endsWith = function (text, char) {
        return text.indexOf('[]') >= 0;
    };

    var matches = function (item, path, value) {
        if(value === undefined || value === "")  return true;

        var pathIndexes = path.split(".");
        var pathIndex = pathIndexes[0];
        if (endsWith(pathIndex, '[]')) {                       // is array
            pathIndex = substringUpto(pathIndex, '[]');
            if (_.isArray(item[pathIndex])) {
                return item[pathIndex].some(function (child) {
                    return matches(child, substringAfter(path, '.'), value);
                })
            }
            return false;
        }
        else {                                               // is  object
            if (pathIndexes.length > 1 && item[pathIndexes[0]]) {
                return matches(item[pathIndexes[0]], substringAfter(path, '.'), value);
            }
        }
        return path === "" ? item === value : item[path] === value;
    };

    self.filter = function (items) {
        return items.filter(function (item) {
            return self.filterCriteria.every(function (criteria) {
                var result = matches(item, criteria.name, criteria.value);
                //console.log("filtermatches : ",result," | ",criteria.name ," | ",criteria.value," | ",item.englishTransliteration);
                return  result;
            })
        })

    };

    self.clearFilters = function () {
        self.filterCriteria.forEach(function (criteria) {
            criteria["value"] = undefined;
        });
    };

    self.setFilterCriteria = function(name,value){
        var filterCriteria =_.find(self.filterCriteria,function(criteria){
            return criteria.name === name;
        });
        filterCriteria.value=value;
    };

    self.removeFilterCriteria = function(filterName){
        self.setFilterCriteria(filterName,undefined);
    };


    return self;

};


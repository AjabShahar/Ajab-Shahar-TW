var AjabShahar = AjabShahar || {};
AjabShahar.user = AjabShahar.user || {};
AjabShahar.user.Sieve = function (filterCriteria) {
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

    var findAndMatch = function (item, path, value, method) {
        if (value === undefined || value === "")  return true;

        var pathIndexes = path.split(".");
        var pathIndex = pathIndexes[0];
        if (endsWith(pathIndex, '[]')) {                       // is array
            pathIndex = substringUpto(pathIndex, '[]');
            if (_.isArray(item[pathIndex])) {
                return item[pathIndex].some(function (child) {
                    return findAndMatch(child, substringAfter(path, '.'), value);
                })
            }
            return false;
        }
        else {                                               // is  object
            if (pathIndexes.length > 1 && item[pathIndexes[0]]) {
                return findAndMatch(item[pathIndexes[0]], substringAfter(path, '.'), value);
            }
        }
        return path === "" ? match(value, item, method) : match(value, item[path], method);
    };

    var match = function (valueInCriteria, valueInObject, method) {
        if (method === "startsWith") {
            return valueInObject.toLowerCase().indexOf(valueInCriteria.toLowerCase()) === 0;
        }
        return valueInCriteria === valueInObject;
    };

    self.filter = function (items) {
        return items.filter(function (item) {
            return self.filterCriteria.every(function (criteria) {
                var result = findAndMatch(item, criteria.name, criteria.value, criteria.method);
                return result;
            })
        })

    };

    self.clearFilters = function () {
        self.filterCriteria.forEach(function (criteria) {
            criteria["value"] = undefined;
        });
    };

    self.clearFiltersWithDisplayName = function () {
        self.filterCriteria.forEach(function (criteria) {
            if (!_.isEmpty(criteria.displayName)) {
                criteria["value"] = undefined;
            }
        });
    };
    self.setFilterCriteria = function (name, value) {
        var filterCriterion = _.find(self.filterCriteria, function (criteria) {
            return criteria.name === name;
        });
        filterCriterion.value = value;
    };

    self.removeFilterCriteria = function (filterName) {
        self.setFilterCriteria(filterName, undefined);
    };


    return self;

};


describe("people model", function () {
    it("should construct people objects", function () {
        personDetails = new AjabShahar.peopleModel(person);

        expect(personDetails.id).toBe(1);
        expect(personDetails.name).toBe("Parvathy Baul");
        expect(personDetails.occupations.length).toBe(2);
        expect(personDetails.thumbnailImg).toBeNull();
        expect(personDetails.profile).toBeNull();
    });

    var person =
    {
        "id": 1,
        "firstName": "Parvathy",
        "middleName": "",
        "lastName": "Baul",
        "firstNameInHindi": null,
        "middleNameInHindi": null,
        "lastNameInHindi": null,
        "roles": [
            "Poet"
        ],
        "primaryOccupation": {
            "id": 9,
            "name": "Poet",
            "categoryType": "person"
        },
        "thumbnailURL": null,
        "profile": null
    };
});
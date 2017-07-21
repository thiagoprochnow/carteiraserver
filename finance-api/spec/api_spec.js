handlers = require('../handler.js');

describe('Finance API', function() {

    describe('handlers', function() {

        it('stocksCreateEvent refuses empty body', function() {
            var response = '';
            var context = '';
            var event = '';

            handlers.stocksCreateEvent(event, context, function (error, ret) {
                response = error;
            });
            expect(response).toEqual(new Error('Body need contains item as JSON'));
        });
    });
    describe('Stocks modules', function() {
    });
});

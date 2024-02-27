import ballerina/test;
import ballerina/io;

@test:Config {}
function testlistTopics() returns error? {
    Client client1 = check new();
    string listTopics = check client1.listTopics("kinetic-magnet-415011");
    io:println("List of topics: " + listTopics);
    test:assertNotEquals(listTopics, ());
}

const fs = require("fs");
const xml2js = require("xml2js");
const parser = new xml2js.Parser();
const builder = new xml2js.Builder();

const pomPath = "./pom.xml";
const newVersion = process.argv[2];

fs.readFile(pomPath, (err, data) => {
  if (err) {
    console.error("Error reading pom.xml:", err);
    process.exit(1);
  }
  parser.parseString(data, (err, result) => {
    if (err) {
      console.error("Error parsing pom.xml:", err);
      process.exit(1);
    }
    result.project.version = [newVersion];
    const updatedPom = builder.buildObject(result);
    fs.writeFile(pomPath, updatedPom, (err) => {
      if (err) {
        console.error("Error writing pom.xml:", err);
        process.exit(1);
      }
      console.log(`Updated pom.xml to version ${newVersion}`);
    });
  });
});

package template;

class DocApp {
	// TEMPLATE METHOD
	final void openDocument(String name) {
		if (!canOpenDocument(name)) {
			// cannot handle this document return;
		}
		Document doc = doCreateDocument();
		if (doc) {
			doc.addDocument(doc);
			aboutToOpenDocument(doc);
			doc.open();
			doc.doRead();
		}
	}

	abstract boolean canOpenDocument(String); // PRIMITIVE METHOD

	abstract aboutToOpenDocument(Document); // PRIMITIVE METHOD

	abstract Document doCreateDocument(); // PRIMITIVE METHOD
}

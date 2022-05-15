
class UpperCaseTransformer implements MyTransformer<String> {

	public String transformElement(String s) {
		return s.toUpperCase();
	}

}

// Add your transformers here

class LowerCaseTransformer implements MyTransformer<String>{
    @Override
    public String transformElement(String s) {
        return s.toLowerCase();
    }
}

class MiddleLetterTransformer implements MyTransformer<String>{
    @Override
    public String transformElement(String s) {
        return String.format("%s",s.charAt(s.length()/2));
    }
}

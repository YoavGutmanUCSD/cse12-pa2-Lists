
class LongWordChooser implements MyChooser<String> {

	@Override
	public boolean chooseElement(String s) {
		return s.length() > 5;
	}

} 

// Add your choosers here

class LowerCaseChooser implements MyChooser<String> {
    @Override
    public boolean chooseElement(String s) {
        return s.equals(s.toLowerCase());
    }
}

class UpperCaseChooser implements MyChooser<String> {
    @Override
    public boolean chooseElement(String s) {
        return s.equals(s.toUpperCase());
    }
}


package application;

import java.text.DecimalFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ConverterController {
	//units for length
	ObservableList<String> length = FXCollections
			.observableArrayList("ft", "in", "m", "cm","mm");
	//units for weight
	ObservableList<String> weight = FXCollections
			.observableArrayList("lb", "oz", "kg", "g");
	//units for temperature
	ObservableList<String> temp = FXCollections
			.observableArrayList("F", "C", "K");
	
	@FXML
	RadioButton lengthChoice;
	@FXML
	RadioButton weightChoice;
	@FXML
	RadioButton tempChoice;
	@FXML
	ComboBox<String> fromUnit;
	@FXML
	ComboBox<String> toUnit;
	@FXML
	TextField fromValue;
	@FXML
	TextField toValue;
	

	//loads the units for length
	@FXML 
	private void lengthSelected(){
			fromUnit.setValue("Length");
			fromUnit.setItems(length);
			toUnit.setValue("Length");
			toUnit.setItems(length);
	}
	//loads the units for weight
	@FXML
	private void weightSelected(){
		fromUnit.setValue("Weight");
		fromUnit.setItems(weight);
		toUnit.setValue("Weight");
		toUnit.setItems(weight);
	}
	//loads the units for temperature	
	@FXML
	private void tempSelected(){
		fromUnit.setValue("Temp");
		fromUnit.setItems(temp);
		toUnit.setValue("Temp");
		toUnit.setItems(temp);
	}
	//action performed when you click the convert button	
	@FXML
	private void convertValues(){
		Double fromValueNumber = Double.parseDouble(fromValue.getText());
		String fromValueType = fromUnit.getValue();
		String toValueType = toUnit.getValue();
		Double convertedValue = 0.0;
		DecimalFormat convertedValueFormatted = new DecimalFormat("0.####");;
		
		//runs a difference conversion depending on which radio button is checked
		if(tempChoice.isSelected()){
			convertedValue = convertTemp(fromValueNumber,fromValueType,toValueType);
		}else if(lengthChoice.isSelected()){
			convertedValue = convertLength(fromValueNumber,fromValueType,toValueType);
		}else if(weightChoice.isSelected()){
			convertedValue = convertWeight(fromValueNumber, fromValueType, toValueType);
		}
		
		//sets the converted value to the text field box
		toValue.setText(convertedValueFormatted.format(convertedValue));
	}
	//converts temperature
	private Double convertTemp(Double fromValueNumber,String fromValueType,String toValueType){
		Double convertedValue = 0.0;
		if(fromValueType.equals("C")){
			if(toValueType.equals("F")){
				convertedValue = (fromValueNumber*1.8)+32.0;
			}else if(toValueType.equals("K")){
				convertedValue = (fromValueNumber+273.15);
			}else if(toValueType.equals("C")){
				convertedValue = fromValueNumber;
			}
		}else if(fromValueType.equals("F")){
			if(toValueType.equals("C")){
				convertedValue = (fromValueNumber-32.0)*(0.55555);
			}else if(toValueType.equals("K")){
				convertedValue = (fromValueNumber + 459.67)*(0.55555);
			}else if(toValueType.equals("F")){
				convertedValue = fromValueNumber;
			}
		}else if(fromValueType.equals("K")){
			if(toValueType.equals("F")){
				convertedValue = (fromValueNumber-273.15)*1.8 + 32.0;
			}else if(toValueType.equals("C")){
				convertedValue = (fromValueNumber-273.15);
			}else if(toValueType.equals("K")){
				convertedValue = fromValueNumber;
			}	
		}		
		return convertedValue;
	}
	//converts length
	private Double convertLength(Double fromValueNumber,String fromValueType,String toValueType){
	Double convertedValue = 0.0;
	if(fromValueType.equals("ft")){
		if(toValueType.equals("in")){
			convertedValue = fromValueNumber * 12;
		}else if(toValueType.equals("m")){
			convertedValue = fromValueNumber * 0.3048;
		}else if(toValueType.equals("cm")){
			convertedValue = fromValueNumber * 30.48;
		}else if(toValueType.equals("mm")){
			convertedValue = fromValueNumber * 304.8;
		}else if(toValueType.equals("ft")){
			convertedValue = fromValueNumber;
		}
	}else if(fromValueType.equals("in")){
		if(toValueType.equals("ft")){
			convertedValue = fromValueNumber/12.0;
		}else if(toValueType.equals("m")){
			convertedValue = fromValueNumber* 0.0254;
		}else if(toValueType.equals("cm")){
			convertedValue = fromValueNumber * 2.54;
		}else if (toValueType.equals("mm")){
			convertedValue = fromValueNumber * 25.4;
		}else if(toValueType.equals("in")){
			convertedValue =  fromValueNumber;
		}
	}else if(fromValueType.equals("m")){
		if(toValueType.equals("ft")){
			convertedValue = fromValueNumber * 3.28084;
		}else if(toValueType.equals("in")){
			convertedValue = fromValueNumber * 39.3701 ;
		}else if(toValueType.equals("cm")){
			convertedValue = fromValueNumber * 100;
		}else if (toValueType.equals("mm")){
			convertedValue = fromValueNumber * 1000;
		}else if(toValueType.equals("m")){
			convertedValue =  fromValueNumber;
		}
	}else if(fromValueType.equals("cm")){
			if(toValueType.equals("ft")){
				convertedValue = fromValueNumber * 0.0328084;
			}else if(toValueType.equals("in")){
				convertedValue = fromValueNumber * 0.393701 ;
			}else if(toValueType.equals("m")){
				convertedValue = fromValueNumber / 100;
			}else if (toValueType.equals("mm")){
				convertedValue = fromValueNumber * 10;
			}else if(toValueType.equals("cm")){
				convertedValue =  fromValueNumber;
			}
	}else if(fromValueType.equals("mm")){
		if(toValueType.equals("ft")){
			convertedValue = fromValueNumber * 0.00328084;
		}else if(toValueType.equals("in")){
			convertedValue = fromValueNumber * 0.0393701 ;
		}else if(toValueType.equals("m")){
			convertedValue = fromValueNumber / 1000;
		}else if (toValueType.equals("cm")){
			convertedValue = fromValueNumber / 10;
		}else if(toValueType.equals("mm")){
			convertedValue =  fromValueNumber;
		}
	}
	return convertedValue;
	}
	//converts weight
	private Double convertWeight(Double fromValueNumber,String fromValueType,String toValueType){
		Double convertedValue = 0.0;
		if(fromValueType.equals("lb")){
			if(toValueType.equals("oz")){
				convertedValue = fromValueNumber*16;
			}else if(toValueType.equals("kg")){
				convertedValue = fromValueNumber * 0.453592;
			}else if(toValueType.equals("g")){
				convertedValue = fromValueNumber * 453.592;
			}else if(toValueType.equals("lb")){
				convertedValue = fromValueNumber;
			}
		}else if(fromValueType.equals("oz")){
			if(toValueType.equals("lb")){
				convertedValue = fromValueNumber * 0.0625;
			}else if(toValueType.equals("kg")){
				convertedValue = fromValueNumber * 0.0283495;
			}else if(toValueType.equals("g")){
				convertedValue = fromValueNumber * 28.3495;
			}else if(toValueType.equals("oz")){
				convertedValue = fromValueNumber;
			}
		}else if(fromValueType.equals("kg")){
			if(toValueType.equals("lb")){
				convertedValue = fromValueNumber*2.20462;
			}else if(toValueType.equals("oz")){
				convertedValue = fromValueNumber * 35.274;
			}else if(toValueType.equals("g")){
				convertedValue = fromValueNumber * 1000;
			}else if(toValueType.equals("kg")){
				convertedValue = fromValueNumber;
			}
		}else if(fromValueType.equals("g")){
			if(toValueType.equals("lb")){
				convertedValue = fromValueNumber * 0.00220462;
			}else if(toValueType.equals("oz")){
				convertedValue = fromValueNumber * 0.035274;
			}else if(toValueType.equals("kg")){
				convertedValue = fromValueNumber / 1000;
			}else if(toValueType.equals("g")){
				convertedValue = fromValueNumber;
			}
		}
		return convertedValue;
	}
	//allows you to switch the from and to units
	@FXML
	private void switchUnits(){
		String tempFromUnit = fromUnit.getValue();
		String tempToUnit = toUnit.getValue();
		fromUnit.setValue(tempToUnit);
		toUnit.setValue(tempFromUnit);
		convertValues();
	}
}
	
	

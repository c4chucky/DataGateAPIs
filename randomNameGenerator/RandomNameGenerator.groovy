import java.util.Random;

import com.kms.katalon.core.annotation.Keyword

@Keyword
def randomName() {

	// Generate Random Names
	String[] names = [
		'Chris',
		'Joe',
		'Susan',
		'Kate',
		'Ieuan',
		'Khushi',
		'Floyd',
		'Noah',
		'Charles',
		'William',
		'Mihow',
		'Angie',
		'Mehul',
		'Brad',
		'Morgan'
	];

	String name = names[ new Random().nextInt(names.length)];
	return name;
}

@Keyword
def randomSurname() {

	String[] surnames = [
		'Kaufman',
		'Francis',
		'Freeman',
		'Olson',
		'Clemons',
		'Hood',
		'Parker',
		'Chester',
		'Hough',
		'Knott',
		'Ahmed',
		'Brown',
		'Pillay',
		'Rogan',
		'Pitt',
		'van Rensburg',
		'du Preez',
		'Smith'
	];

	String surname = surnames[ new Random().nextInt(surnames.length)];
	return surname;
}
